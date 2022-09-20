(ns sicp.ch1.ex2-5
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             ;; [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-35 :refer [abs]]
             [sicp.ch1.examples :refer [square]]))

(define (expt b n)
  (cond ((= n 0) 1)
        ((even? n) (square (fast-expt b (/ n 2))))
        (else (* b (fast-expt b (- n 1))))))



(define (cons x y)
  (* (expt 2 x) (expt 3 y)))

(define (uncons n b)
  (define (iter n acc) 
    (if (= 0 (mod n b))
      (iter (/ n b) (+ 1 acc))
      acc))
  (iter n 0))

(define (car z)
  (uncons z 2))

(define (cdr z)
  (uncons z 3))

(println (car (cons 18 6)))
(println (cdr (cons 0 9)))






