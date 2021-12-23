(ns sicp.ch1.ex1-3
  (:refer-clojure :exclude [cond define])
   (:require 
    [sicp.scheme.core :refer :all]
    [sicp.ch1.examples :refer [sum-of-squares]]))

;; Scheme
(define (sum-largest-squares x y z)
  (cond ((and (<= x y) (<= x z)) (sum-of-squares y z))
          ((and (<= y x) (<= y z)) (sum-of-squares x z))
          (else (sum-of-squares x y))))

;; Clojure

(defn sum-largest-squares [x y z]
  (clojure.core/cond
    (and (<= x y) (<= x z)) (sum-of-squares y z)
    (and (<= y x) (<= y z)) (sum-of-squares x z)
    :else (sum-of-squares x y)))





