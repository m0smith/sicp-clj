(ns sicp.scheme.cons
  (:refer-clojure :exclude [cons])
  (:require [sicp.scheme.core :refer [define]]))

(define (cons x y) [x y])

(define car first)

(define cdr second)
