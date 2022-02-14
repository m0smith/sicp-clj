(ns sicp.ch1.ex1-35
  ""
   (:refer-clojure :exclude [cond define try let next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

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


(double (fixed-point (lambda (x) (+ 1 (/ 1 x))) 1))

