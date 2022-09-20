(ns sicp.ch1.ex2-4
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             ;; [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-35 :refer [abs]]))

(define (cons x y)
  (lambda (m) (m x y)))

(define (car z)
  (z (lambda (p q) p)))

(define (cdr z)
  (z (lambda (p q) q)))

(println (car (cons 0 9)))
(println (cdr (cons 0 9)))






