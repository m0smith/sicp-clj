(ns sicp.ch1.ex1-46
  ""
   (:refer-clojure :exclude [cond define try let next double])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

(define (iterative-improve good-enough-fn improve-guess-fn)
  (define :recur (iter guess last-guess)
    (if (good-enough-fn guess last-guess)
      guess
      (iter (improve-guess-fn guess) guess)))
  (lambda (guess) (iter (improve-guess-fn guess) guess)))

(define (abs x)
  (if (< x 0)
    (- x)
    x))

(define (average x y)
  (/ (+ x y) 2))

(define (sqrt guess x) 
  (define (good-enough? guess _)
    (< (abs (- (square guess) x)) 0.001))
  (define (improve guess)
    (average guess (/ x guess)))
  (float ((iterative-improve good-enough? improve)
          guess)))

(println (sqrt 4 25))
(println (sqrt 4 100))

(define tolerance 0.00001)

(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  ((iterative-improve close-enough? f) first-guess))

(println (fixed-point cos 1.0))
(println (fixed-point (lambda (y) (+ (sin y) (cos y)))
                      1.0))


