(ns sicp.ch1.ex1-38
  ""
   (:refer-clojure :exclude [cond define try let next])
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
               (+ (d k) prev-term)))))
  (iter (- k 1)
        (/ (n k) (d k))))

;; Clojure has a function constantly that will return a constant value when invoked
(define (nf x) 1)

(define (df x)
  (let ((d (+ x 1)))
    (if (= (remainder d 3) 0)
      (* 2 (/ d 3))
      1)))

;; Check that `df` works
(for [x (range 1 11)]
  (df x))

;; Function to estimate e - 2
(define (e k)
  (cont-frac nf df k))

;; The expected value
(- (Math/E) 2)

;; The actual estimate
(e 100)
