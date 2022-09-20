(ns sicp.ch1.ex1-41
  ""
   (:refer-clojure :exclude [cond define try let next double])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

(define (double f)
  (lambda (x)
          (f (f x))))
((double inc) 0)

(((double (double double)) inc) 5)

