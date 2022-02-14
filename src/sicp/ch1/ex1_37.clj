(ns sicp.ch1.ex1-37
  ""
   (:refer-clojure :exclude [cond define try let next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))


(define (cont-frac n d k)
  (define :recur (iter k prev-term)
    (if (= k 0)
      prev-term
      (iter (- k 1)
            (/ (n k)
               (+ (d k) prev-term)))))
  (iter (- k 1)
        (/ (n k) (d k))))

(define (cont-frac-recursive n d k)
  (define (rec i)
    (if (> i k)
      0
      (/ (n i)
         (+ (d i) (rec (+ 1 i))))))
  (rec 1))

(cont-frac (lambda (x) 1)
           (lambda (x) 1)
           10)

(cont-frac-recursive (lambda (x) 1)
                     (lambda (x) 1)
                     10)

(cont-frac (lambda (x) 1)
           (lambda (x) 1)
           100)

(cont-frac (lambda (x) 1)
           (lambda (x) 1)
           1000)
