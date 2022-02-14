(ns sicp.ch1.ex1-36
  ""
   (:refer-clojure :exclude [cond define try let next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

(log 1)

(define tolerance 0.00001)

(define (abs x)
  (cond ((> x 0) x)
        ((= x 0) 0)
        ((< x 0) (- x))))

(defonce !taps (atom ()))

(defn tapped [x]
  (swap! !taps conj x))

(defonce setup3
  (do
    (add-tap (bound-fn* println))
    (add-tap tapped)))

;; ## NOTE
;; `try` is a Clojure builtin and you cannot use it as a function name
(define (fixed-point f first-guess)
  (define (close-enough? v1 v2)
    (< (abs (- v1 v2)) tolerance))
  (define :recur (try2 guess)
    (tap> guess)
    (let ((next (f guess)))
      (if (close-enough? guess next)
          next
          (try2 next))))
  (try2 first-guess))

(fixed-point (lambda (x) (/ (log 1000) (log x))) 10)

(clerk/table {:column-1 @!taps})
