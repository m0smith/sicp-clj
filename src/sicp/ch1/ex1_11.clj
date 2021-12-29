(ns sicp.ch1.ex1-11
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [good-enough? improve]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sceheme
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (f-recur n)
  (if (< n 3)
    n
    (+ (f-recur (- n 1))
       (* 2 (f-recur (- n 2)))
       (* 3 (f-recur (- n 3))))))

(define (f-iter n)
  (define (f-iter* n fn-1 fn-2 fn-3)
    (if (< n 3)
      fn-1
      (f-iter* (dec n) 
               (+ fn-1
                  (* 2 fn-2)
                  (* 3 fn-3))
               fn-1
               fn-2)))
  (if (< n 3)
    n
    (f-iter* n 2 1 0)))

;; sicp.ch1.ex1-11> (map f-recur (range 10))
;; (0 1 2 4 11 25 59 142 335 796)
;; sicp.ch1.ex1-11> (map f-iter (range 10))
;; (0 1 2 4 11 25 59 142 335 796)
