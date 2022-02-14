  (ns sicp.ch1.ex1-23
  ""
   (:refer-clojure :exclude [cond define next])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [square]]))

(define (divides? a b)
  (= (remainder b a) 0))

(define (next n)
  (if (= n 2) 
    3
    (+ 2 n)))

(define (find-divisor-odds n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor-odds n (next test-divisor)))))

(define (smallest-divisor n)
  (find-divisor-odds n 2))

