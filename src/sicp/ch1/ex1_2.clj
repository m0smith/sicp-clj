(ns sicp.ch1.ex1-2
   (:refer-clojure :exclude [cond define + - * /])
   (:require [sicp.scheme.core :refer :all]))

(defn + [& args]
  (let [rtnval (apply clojure.core/+ args)]
    (println "+" args "=" rtnval)
    rtnval))

(defn - [& args]
  (let [rtnval (apply clojure.core/- args)]
    (println "-" args "=" rtnval)
    rtnval))

(defn * [& args]
  (let [rtnval (apply clojure.core/* args)]
    (println "*" args "=" rtnval)
    rtnval))

(defn / [& args]
  (let [rtnval (apply clojure.core// args)]
    (println "/" args "=" rtnval)
    rtnval))

(/ (+ 5 4 (- 2 3 (+ 6 (/ 4 5))))
   (* 3 
      (- 6 2)
      (- 2 7)))

