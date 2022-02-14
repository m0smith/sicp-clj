(ns sicp.ch1.ex1-32
  ""
   (:refer-clojure :exclude [cond define next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]))

;; https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-12.html

;; ## From Ex 1.31

(define (product-recursive term a next b)
  (if (> a b)
      1
      (* (term a)
         (product-recursive term (next a) next b))))

(define (product-iterative term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (* result (term a)))))
  (iter a 1))

(define (accumulate-recursive combiner null-value term a next b)
  (if (> a b)
      null-value
      (combiner (term a)
         (product-recursive term (next a) next b))))

(define (accumulate-iterative combiner null-value term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (combiner result (term a)))))
  (iter a null-value))

;; ## Clojure 

(defn accumulate-iterative [combiner null-value term a next b]
  (letfn
    [(iter [a result] (if (> a b) 
                        result 
                        (recur (next a) (combiner result (term a)))))]
    (iter a null-value)))

;; ## Examples

(define (! n)
  (accumulate-iterative * 1 identity 2N inc n))

(! 10)

((juxt count identity type) (str (! 1000)))
