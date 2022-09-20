(ns sicp.ch1.ex1-42
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

((compose square inc) 6)
