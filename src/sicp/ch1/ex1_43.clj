(ns sicp.ch1.ex1-43
  ""
   (:refer-clojure :exclude [cond define try let next double])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

;; ## NOTE
;; Clojure implements this as `comp`
(define (compose f g)
  (lambda (x)
          (f (g x))))

(define (repeated f n)
  (if (> n 1)
    (compose f (repeated f (- n 1)))
    f))

((repeated inc 4) 1)

((repeated square 2) 5)

