(ns sicp.ch1.ex1-44
  ""
   (:refer-clojure :exclude [cond define try let next double])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

;; ## NOTE
;; Clojure implements this as `comp`
(define (compose f g)
  (lambda (x)
          (f (g x))))

(define (repeated f n)
  (if (> n 1)
    (compose f (repeated f (- n 1)))
    f))

(define dx 0.000001)

(define (smooth f)
  (lambda (x)
          (/ (+ (f x)
                (f (- x dx))
                (f (+ x dx)))
             3)))

(define (n-fold-smooth f n)
  ((repeated smooth n) f))

((n-fold-smooth square 15) 4)
