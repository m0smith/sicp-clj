(ns sicp.ch1.ex1-40
  ""
   (:refer-clojure :exclude [cond define try let next double])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

(define dx 0.00001)

(define tolerance 0.00001)

(define (abs x)
  (if (< x 0)
    (- x)
    x))

(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define :recur (try2 guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
          (try2 next))))
  (try2 first-guess))

(define (deriv g)
  (lambda (x)
    (/ (- (g (+ x dx)) (g x))
       dx)))

(define (newton-transform g)
  (lambda (x)
    (- x (/ (g x) ((deriv g) x)))))

(define (newtons-method g guess)
  (fixed-point (newton-transform g) guess))

(define (cubic a b c)
  (lambda (x)
          (+ (* x x x)
             (* a x x)
             (* b x)
             c)))

(newtons-method (cubic 1 1 1) 10)
