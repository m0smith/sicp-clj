(ns sicp.cons
  ""
   (:refer-clojure :exclude [cons atom])
   )

;; BNuilding LISP from the ground up with only
;;  fn
;;  cond
;;  dec


(defn cons [a b]
  (with-meta (fn [f] (f a b))
    {:cons-cell true}))

(defn car [cons-cell]
  (cons-cell (fn [a _] a)))

(defn cdr [cons-cell]
  (cons-cell (fn [_ b] b)))

(defn c*r [f n cons-cell]
  (cond (= n 0) cons-cell
        :else (recur f (dec n)(f cons-cell))))

(def cddr (fn [cc] (c*r cdr 2 cc)))

(defn atom [o]
  (-> o meta :cons-cell not))


