(ns sicp.ch1.ex1-34
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

(define (f g)
  (g 2))

(f square)

(f (lambda (z) (* z (+ z 1))))

;; (f f) => (f 2) => (2 2) => Long cannot be cast to IFn




