(ns sicp.ch1.examples
  "Chapter one example code"
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Scheme
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (square x) (* x x))

(define (sum-of-squares x y)
  (+ (square x) (square y)))

;; Section 1.1.7 Newton's Method
(define (abs x)
  (if (< x 0)
      (- x)
      x))

(define (average x y)
  (/ (+ x y) 2))

(define (improve guess x)
  (average guess (/ x guess)))

(define (good-enough? guess x)
  (< (abs (- (square guess) x)) 0.001))

(define (sqrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x)
                 x)))

(define (sqrt x)
  (sqrt-iter 1.0 x))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Clojure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn square [x] 
  (* x x))

(defn sum-of-squares [x y] 
  (+ (square x) (square y)))

(defn abs [x] 
  (if (< x 0) (- x) x))

(defn average [x y] 
  (/ (+ x y) 2))

(defn improve [guess x] 
  (average guess (/ x guess)))

(defn good-enough? [guess x] 
  (< (abs (- (square guess) x)) 0.001))

;; Note tail call recursion is implemented with `recur`
(defn sqrt-iter [guess x]
  (if (good-enough? guess x) 
    guess 
    (recur (improve guess x) x)))

(defn sqrt [x] 
  (sqrt-iter 1.0 x))
