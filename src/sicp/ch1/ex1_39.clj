(ns sicp.ch1.ex1-39
  ""
   (:refer-clojure :exclude [cond define try let next double])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

;; ## NOTE
;; Using :recur to require the tail call optimization
(define (cont-frac n d k)
  (define :recur (iter k prev-term)
    (if (= k 0)
      prev-term
      (iter (- k 1)
            (/ (n k)
               (- (d k) prev-term)))))
  (iter (- k 1)
        (/ (n k) (d k))))

;; Have to exclude double as it is a built in
(define (double x) (+ x x))

(define (nth-odd n)
  (+ 1 (double (- n 1))))

;; Check that `nth-odd` works
(for [x (range 1 11)]
  (nth-odd x))

(define (tan-cf x k)
  (define (nf n)
    (if (= 1 n)
      x
      (square x)))
  (cont-frac nf nth-odd k))

;; expect result
0.4227932187381617619816354271652903339419897727156935898473309413

;; computed result
(tan-cf 0.4 500)

