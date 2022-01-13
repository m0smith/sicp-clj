(ns sicp.ch1.ex1-16
  ""
   (:refer-clojure :exclude [cond define even?])
   (:require [sicp.scheme.core :refer :all]))

(define (square x) (*' x x))

(define (even? x)
  (= (remainder x 2) 0))

;; Scheme

(define (exp b n)
  (define (exp* a b n)
    (cond 
      ((= n 0) a)
      ((even? n) (exp* a (square b) (/ n 2)))
      (else (exp* (* a b) b (dec n)))))
  (exp* 1 b n))

;;  Clojure

;; This matches the spirit of the Scheme, using just the tools we have
;; thus far into the book

(defn exp [b n]
  (letfn [(exp* [a b n]
            (clojure.core/cond
              (= n 0) a
              (even? n) (recur a (square b) (/ n 2))
              :else (recur (*' a b) b (dec n))))]
    (exp* 1 b n)))

;;  For real world exp/power, we can use 
;; the  java interop BigInteger.pow
;;

(defn expj [b n]
  (.pow (biginteger b) n))



