(ns sicp.ch1.ex1-30
  ""
   (:refer-clojure :exclude [cond define next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

;; https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-12.html
;; ## Sum
;; Add (term a) from a to b by (next a)
(define (sum term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (+ result (term a)))))
  (iter a 0))

;; ## Clojure


(defn sum [term a next b]
  (letfn [(iter [a result] (if (> a b) 
                             result 
                             (recur (next a) (+ result (term a)))))]
    (iter a 0)))

(sum identity 1 inc 10)
