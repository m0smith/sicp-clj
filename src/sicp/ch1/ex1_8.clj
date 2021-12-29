(ns sicp.ch1.ex1-8
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [square abs average]]))

(define (percent-diff a b)
  (define x1 (min a b))
  (define x2 (max a b))
  (/ (- x2 x1) x1))

(define (twice x) (+ x x))

(define (cube x) (* x x x))

(define (improve guess x)
  (/ (+ (/ x (square guess))
        (twice guess))
     3))

(define (good-enough? guess last-guess)
  (and last-guess
       (< (percent-diff guess last-guess) 0.0000001)))

(define (cube-root-iter guess last-guess x)
  (if (good-enough? guess last-guess)
      guess
      (cube-root-iter (improve guess x) guess x)))

(define (cube-root x)
  (cube-root-iter 1.0 nil x))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Clojure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn percent-diff [a b]
  (let [x1 (min a b) 
        x2 (max a b)] 
    (/ (- x2 x1) x1)))

(defn twice [x] 
  (+ x x))

(defn cube [x] 
  (* x x x))

(defn improve [guess x]
  (/ (+ (/ x (square guess)) (twice guess)) 3))

(defn good-enough? [guess last-guess]
  (and last-guess 
       (< (percent-diff guess last-guess) 1.0E-7)))

(define (cube-root-iter guess last-guess x)
  (if (good-enough? guess last-guess)
      guess
      (cube-root-iter (improve guess x) guess x)))

(define (cube-root x)
  (cube-root-iter 1.0 nil x))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Clojure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn percent-diff [a b]
  (let [x1 (min a b) 
        x2 (max a b)] 
    (/ (- x2 x1) x1)))

(defn twice [x] 
  (+ x x))

(defn cube [x] 
  (* x x x))

(defn improve [guess x]
  (/ (+ (/ x (square guess)) (twice guess)) 3))

(defn good-enough? [guess last-guess]
  (and last-guess 
       (< (percent-diff guess last-guess) 1.0E-7)))

(defn cube-root-iter [guess last-guess x]
  (if (good-enough? guess last-guess)
    guess
    (cube-root-iter (improve guess x) guess x)))

(defn cube-root [x] 
  (cube-root-iter 1.0 nil x))
