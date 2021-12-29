(ns sicp.scheme.core
   (:refer-clojure :exclude [cond define])
   (:require [clojure.tools.macro :refer [macrolet]]))


(defn inner-define-fn? 
  ([] 
   false)
  ([form] 
   ;;(println "inner-define-fn?" form)
   (and (list? form) (= 'define (first form)) (-> form second list?))))

(defn inner-define? 
  ([] 
   false)
  ([form]
   (and (list? form) (= 'define (first form)) (-> form second list? not))))

(defn inner-define-fn 
  [[_ [n & args] & body]]
  `(~n ~(vec args) (do+ ~@body)))

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
  (cons (define-form-type (first forms)) forms))

(defn define-body [body]
  (let [parts (reverse (map add-fn (partition-by define-form-type body)))
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
  (if (list? n)
    `(defn ~(first n) ~(into [] (next n))
       ~(define-body body))
    `(def ~n 
       ~(define-body body))))

(defmacro cond [& exprs]
  (cons 'clojure.core/cond (mapcat (fn [[p e :as args]] (if (= p 'else) (list :else e) args )) exprs)))

(defmacro do+ [& body]
  (if (< (count body) 2)
    (first body)
    `(do ~@body)))
