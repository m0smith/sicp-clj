(ns sicp.scheme.core
   (:refer-clojure :exclude [cond define])
   (:require [clojure.tools.macro :refer [macrolet]]))

(defn define-top-level [n body]
  `(def ~n ~@body))

#_(defn define-top-level-fn [[n & args] body]
  (let [define 'define
        n2 'n2
        bdy 'bdy]
    `(macrolet [(~define [n2# & bdy#] 
                 (let [fn-n# (first n2#)
                       fn-args# (second n2#)]
                   (list 'letfn [(apply list fn-n#  (vector fn-args#) bdy#)]))
                 )]
               (defn ~n ~(vec args) ~@body))))


(defn inner-define-fn? 
  ([] 
   false)
  ([form]
   (and (seq form) (= 'define (first form)) (-> form second seq))))

(defn inner-define-fn 
  [[f [n & args] & body]]
  `(~n ~(vec args) ~@body))

(defn define-top-level-fn [[n & args] body]
  (let [inner-def-fns (apply list (map inner-define-fn (take-while inner-define-fn? body)))
        b (into '() (drop-while inner-define-fn? body))]
    `(defn ~n ~(vec args) 
          (letfn ~(into [] inner-def-fns)
                ~@b))))

(defmacro define [n & body]
  (if (list? n)
    (define-top-level-fn n body)
    (define-top-level n body)))

(defmacro cond [& exprs]
  (cons 'clojure.core/cond (mapcat (fn [[p e :as args]] (if (= p 'else) (list :else e) args )) exprs)))
