(ns sicp.scheme.core
   (:refer-clojure :exclude [cond define let])
   (:require [clojure.tools.macro :refer [symbol-macrolet defsymbolmacro with-symbol-macros]]))

;; ## A scheme DSL for Clojure
;; The main differences are
;; - The ordering of functions matters in Clojure
;; - `try` cannot be used as a function name
;; - Clojure requires a the use of `recur` to simulate tail call optimization.  Use the :recur keyword after `define` to generate code that has recur
;;
;; An example of using :recur
;;
;;     (define (! n)
;;       (define :recur (iter acc n)
;;         (if (< n 2)
;;         acc
;;         (iter (*  n acc) (dec n))))
;;       (iter 1 n))

(defn inner-define-fn? 
  ([] 
   false)
  ([form] 
   ;;(println "inner-define-fn?" form)
   (and (list? form) 
        (= 'define (first form)) 
        (or 
         (-> form second (= :recur))
         (-> form second list?)))))
(defn ppp 
  ([m x r]
   (println m x r)
   r)
  ([m x x2 r]
   (println m x x2 r)
   r))

(defn inner-define? 
  ([] 
   false)
  ([form]
   (if-let [[f v] (and (list? form) form)]
     (ppp "inner-define?" v 
          (and (= 'define f)
               (not= :recur v)
               (not (list? v)))))))

(defn inner-define-fn 
  [[_ x & body]]
  (println "inner-define-fn" x)
  (clojure.core/cond
    (= :recur x)
    (do 
      (println "inner-define-fn" x (first body))
      (clojure.core/let [[n & args] (first body)
                         body (rest body)]
        `(~n ~(vec args) 
          (symbol-macrolet [~n recur] (do+ ~@body)))))

    (clojure.string/ends-with? (name (first x)) ":recur")
    (clojure.core/let [[n & args] x]
      `(~n ~(vec args) 
        (symbol-macrolet [~n recur]
                         (do+ ~@body))))

    :else
    (clojure.core/let [[n & args] x]
      `(~n ~(vec args) (do+ ~@body)))))

(defn inner-define
  [[_ n & body]]
  `(~n ~@body))

(defn define-let 
  ([forms] `(let ~(into [] (mapcat inner-define forms))))
  ([forms inner-forms]
   `(let ~(into [] (mapcat inner-define forms))
      (~@inner-forms))))

(defn define-letfn 
  ([forms] `(letfn ~(into [] (map inner-define-fn forms))))
  ([forms inner-forms]
   (println "define-letfn 2" forms inner-forms)
   `(letfn ~(into [] (map inner-define-fn forms))
      (~@inner-forms))))

(defn define-do 
  ([forms] `(do+ ~@forms))
  ([forms inner-forms]
   ;;(println "do" forms)
   `(do+ ~@forms
        (~@inner-forms))))

(defn define-form-type
  [f]
  ;;(println "define-form-type" f)
  (clojure.core/cond (inner-define? f) define-let
                     (inner-define-fn? f) define-letfn
                     :else define-do))

(defn add-fn [forms]
  ;;(println "add-fn" forms (type forms))
  (clojure.core/let [ft (define-form-type (first forms))]
    ;;(println "add-fn ft" ft)
    (cons ft forms)))

(defn define-body [body]
  (clojure.core/let [parts (reverse (map add-fn (partition-by define-form-type body)))
                     [first-f & first-forms] (first parts)]
    (reduce (fn [acc [f & forms :as el]] 
              (f forms acc)) 
            (first-f first-forms) (next parts))))

(defmacro define 
  "Can either be 
    (define x <expr>) ;; Bind x to <expr>
    (define (foo a b) <body) ;; Create a function `foo` with parameters `a` and `b`

  The `define` form in Scheme maps to several Clojure forms
      Global environment: `def` and `defn`
      Local scope: `let` and `letfn`"
  [n & body]
  (clojure.core/cond 
    (= :recur n)
    (clojure.core/let [[n & body] body]
      `(defn ~(first n) ~(into [] (next n))
        (symbol-macrolet [~(first n) recur]
                         ~(define-body body))))
    
    (and (list? n)
         (clojure.string/ends-with? (name (first n)) ":recur"))
    `(defn ~(first n) ~(into [] (next n))
       (symbol-macrolet [~(first n) recur]
                        ~(define-body body)))
    
    (list? n)
    `(defn ~(first n) ~(into [] (next n))
       ~(define-body body))
    
    :else
    `(def ~n 
       ~(define-body body))))

#_(define (foo x) (+ x x))


(defmacro cond [& exprs]
  (cons 'clojure.core/cond (mapcat (fn [[p e :as args]] (if (= p 'else) (list :else e) args )) exprs)))

#_(defmacro cond** [f]
  `(symbol-macrolet '[else :else] ~f))

#_(defn cond* [f]
  (list (list 'cond** f)))

#_(defmacro cond [& exprs]
  ` ~(cons 'clojure.core/cond (mapcat cond* exprs)))

#_(cond ((= 1 2) false)
        (else true))

(defmacro do+ [& body]
  (if (< (count body) 2)
    (first body)
    `(do ~@body)))

(defmacro lambda [params & body]
  `(fn ~(vec params) ~@body))

(defmacro let [bindings & body]
  `(clojure.core/let ~(vec (mapcat identity bindings)) ~@body))

(define remainder rem)

(define (display x)
  (print x))

(define (runtime)
  (. System nanoTime))

(define (log x) 
  (Math/log x))



