(ns sicp.ch1.ex1-6
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [good-enough? improve]]))

(define (new-if predicate then-clause else-clause)
  (cond (predicate then-clause)
        (else else-clause)))

(define (sqrt-iter guess x)
  (new-if (good-enough? guess x)
          guess
          (sqrt-iter (improve guess x)  ;; This recursive call is always invoked resulting in stack overflow or infinite loop
                     x)))

(defn new-sqrt [x] 
  (sqrt-iter 1.0 x))

;; Results in a stack overflow

;; sicp.ch1.ex1-6> (new-sqrt 9)
;; Execution error (StackOverflowError) at sicp.ch1.examples/abs (examples.clj:48).


