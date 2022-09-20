(ns sicp.ch1.ex2-1
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-33 :refer [gcd]]
             [sicp.ch1.ex1-15 :refer [abs]]))


(define (make-rat n d) (cons n d))

(define (make-rat n d)
  (let ((g (gcd n d)))
    (cons (/ n g) (/ d g))))

(define (numer x) (car x))

(define (denom x) (cdr x))

(define (print-rat x)
  (newline)
  (display (numer x))
  (display "/")
  (display (denom x)))

(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))
(define (equal-rat? x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(define one-third (make-rat 1 3))

(print-rat (add-rat one-third one-third))

;; NOTE:  Clojure has built in ratios

(newline)
(println "Clojure" (+ (/ 1 3) (/ 1 3)))

(define (make-rat n d)
  (define (normalize-sign n)
    (if (< d 0)
      (- n)
      n))
  (let ((g (gcd n (abs d))))
    (cons (normalize-sign (/ n g)) 
          (abs (/ d g)))))

(newline)
(display "Test normalize sign")
(print-rat (make-rat 2 10))
(print-rat (make-rat -4 10))
(print-rat (make-rat 6 -10))
(print-rat (make-rat -8 -10))



