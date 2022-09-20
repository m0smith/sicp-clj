(ns sicp.ch1.ex1-45
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

(define tolerance 0.00001)

(define (abs x)
  (cond ((> x 0) x)
        ((= x 0) 0)
        ((< x 0) (- x))))

;; ## NOTE
;; `try` is a Clojure builtin and you cannot use it as a function name
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define :recur (try2 guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
          (try2 next))))
  (try2 first-guess))

(define (average a b)
  (/ (+ a b)
     2))

(define (average-damp f)
  (lambda (x) (average x (f x))))

;; This is a Scheme built in
(define (expr b x)
  (Math/pow b x))


(define (nth-root n x)
  (define (f y)
    (/ x (expr y (- n 1))))
  (fixed-point ((repeated average-damp (- n 1)) f)
               2.5))

(nth-root 2 81)
(nth-root 3 81)
(nth-root 4 81)

