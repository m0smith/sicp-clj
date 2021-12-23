(ns sicp.ch1.ex1-4
  "The magic is in the fact that the if statement returns a function (+ or -).  If b is positive return + else -

  If b is negative, subtract because subtacting a negative is the same as adding the absolute value"
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]))

;; Scheme
(define (a-plus-abs-b a b)
  ((if (> b 0) + - ) a b))

;; Clojure
(defn clj-a-plus-abs-b [a b] 
  ((if (> b 0) + -) a b))

;; The if statement returns a function

;; sicp.ch1.ex1-4> (if (> 10 0) + -)
;; #function[clojure.core/+]
;; sicp.ch1.ex1-4> (if (> -10 0) + -)
;; #function[clojure.core/-]

;; The outer parens then call that function with `a` and `b` as args
