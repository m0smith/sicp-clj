  (ns sicp.ch1.ex1-21
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [square]]))

(define (divides? a b)
  (= (remainder b a) 0))

(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))

(define (smallest-divisor n)
  (find-divisor n 2))

;; Clojure

(defn find-divisor [n test-divisor]
  (clojure.core/cond
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (recur n (+ test-divisor 1))))

;; sicp.ch1.ex1-21> (smallest-divisor 199)
;; 199
;; sicp.ch1.ex1-21> (smallest-divisor 1999)
;; 1999
;; sicp.ch1.ex1-21> (smallest-divisor 19999)
;; 7


