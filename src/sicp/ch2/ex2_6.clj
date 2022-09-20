(ns sicp.ch1.ex2-6
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             ;; [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-35 :refer [abs]]
             [sicp.ch1.examples :refer [square]]))

(define zero (lambda (f) (lambda (x) x)))
;; (define (zero f) identity)

(define (add-1 n)
  (lambda (f) (lambda (x) (f ((n f) x)))))

(define one (lambda (f) (lambda (x) (f x))))

(define two (lambda (f) (lambda (x) (f (f x)))))
(define three (lambda (f) (lambda (x) (f (f (f x))))))

(define (add a b)
  (lambda (f) (lambda (x) ((a f) ((b f) x)))))

(define (mul a b)
  (lambda (f) (lambda (x) ((a (b f)) x))))

(define (power a b)
  (a b))

(println ((zero inc) 0))
(println (((add-1 zero) inc) 0))
(println ((one inc) 0))
(println (((add-1 (add-1 zero)) inc) 0))
(println ((two inc) 0))
(println (((add one two) inc) 0))
(println (((mul  three three) inc) 0))
(println (((power  three three) inc) 0))

(defn to-int [c] ((c inc) 0))

(println (to-int three))
