(ns sicp.ch1.ex1-17
  ""
   (:refer-clojure :exclude [cond define * double])
   (:require [sicp.scheme.core :refer :all]))

(define (* a b)
  (if (= b 0)
      0
      (+ a (* a (- b 1)))))

(define (double x) (+ x x))

(define (halve x) (/ x 2))

(define (*-fast x y)
  (println x "*" y)
  (cond 
    ((= 1 x) y)
    ((= 1 y) x)
    ((= 0 x) 0)
    ((= 0 y) 0)
    ((< y x) (*-fast y x))
    ((even? x) (*-fast (halve x) (double y)))
    (else (+ y (*-fast (dec x) y)))))
