(ns sicp.ch1.ex1-33
  ""
   (:refer-clojure :exclude [cond define next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]))

;; https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-12.html



(define  (filtered-accumulate pred combiner null-value term a next b)
  (define (iter:recur a result)
    (if (> a b)
        result
        (iter:recur (next a) (if (pred a)
                         result
                         (combiner result (term a))))))
  (iter:recur a null-value))

(filtered-accumulate even? + 0 identity 1 inc 10)

;; ## Part a

(define (square n) (* n n))

(define (divides? a b)
  (= (remainder b a) 0))

(define (find-divisor:recur n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor:recur n (+ test-divisor 1)))))

(define (smallest-divisor n)
  (find-divisor:recur n 2))

(define (prime? n)
  (= n (smallest-divisor n)))

(filtered-accumulate prime? + 0 square 1 inc 10)

;; ## Part b

(define (gcd a b)
  (if (= b 0)
    a
    (recur b (mod a b))))

(define (part-b n)
  (define (pred i)
    (= 1 (gcd i n)))
  (filtered-accumulate pred * 1 identity 1 inc (- n 1)))

(part-b 10)



