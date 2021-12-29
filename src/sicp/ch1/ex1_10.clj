(ns sicp.ch1.ex1-10
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [good-enough? improve]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Scheme
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (A x y)
  (cond ((= y 0) 0)
        ((= x 0) (* 2 y))
        ((= y 1) 2)
        (else (A (- x 1)
                 (A x (- y 1))))))

;; sicp.ch1.ex1-10> (A 1 10)
;; 1024
;; sicp.ch1.ex1-10> (A 2 4)
;; 65536
;; sicp.ch1.ex1-10> (A 3 3)
;; 65536

(define (f n) (A 0 n))

(define (g n) (A 1 n))

(define (h n) (A 2 n))

(define (k n) (* 5 n n))

;; f  = 2x
;; g = x^2
;; h = x^2^2
;; k = 5n^2



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Clojure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn A [x y]
  (cond
    ((= y 0) 0) ((= x 0) (* 2 y))
    ((= y 1) 2) (else (A (- x 1) (A x (- y 1))))))
