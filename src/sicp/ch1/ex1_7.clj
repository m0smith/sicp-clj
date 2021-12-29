(ns sicp.ch1.ex1-7
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [improve sqrt square]]))

#_(define (good-enough? guess x)
  (< (abs (- (square guess) x)) 0.001))

(define (percent-diff a b)
  (define x1 (min a b))
  (define x2 (max a b))
  (/ (- x2 x1) x1))

(define (better-good-enough? guess last-guess) 
  (and last-guess
       (< (percent-diff guess last-guess) 0.00001)))

(define (better-sqrt-iter guess last-guess x)
  (if (better-good-enough? guess last-guess)
      guess
      (better-sqrt-iter (improve guess x) guess x)))

(define (better-sqrt x)
  (better-sqrt-iter 1.0 nil x))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Clojure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn percent-diff [a b]
  (let [x1 (min a b) 
        x2 (max a b)] 
    (/ (- x2 x1) x1)))

(defn better-good-enough? [guess last-guess]
  (and last-guess 
       (< (percent-diff guess last-guess) 1.0E-5)))

(defn better-sqrt-iter [guess last-guess x]
  (if (better-good-enough? guess last-guess)
    guess
    (recur (improve guess x) guess x))) ;; Use `recur` for tail call recursion

(defn better-sqrt [x] 
  (better-sqrt-iter 1.0 nil x))
