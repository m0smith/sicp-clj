(ns sicp.ex1-3
   (:require [sicp.scheme.core :as s]))

(s/define (s-sum-largest-squares x y z)
  (s/cond ((and (<= x y) (<= x z)) (+ (* y y) (* z z)))
          ((and (<= y x) (<= y z)) (+ (* x x) (* z z)))
          (else (+ (* x x) (* y y)))))

(defn sum-largest-squares [x y z]
  (cond
    (and (<= x y) (<= x z)) (+ (* y y) (* z z))
    (and (<= y x) (<= y z)) (+ (* x x) (* z z))
    :else (+ (* x x) (* y y))))





