(ns sicp.ch1.ex2-7
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-35 :refer [abs]]
             [sicp.ch1.examples :refer [square]]))

(define (make-interval a b) (cons (min a b) (max a b)))
(define (lower-bound i) (car i))
(define (upper-bound i) (cdr i))
