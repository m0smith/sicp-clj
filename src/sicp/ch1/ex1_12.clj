(ns sicp.ch1.ex1-12
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [good-enough? improve]]))


(define (pascal-triangle row col)
  (cond 
    ((zero? col) 1)
    ((= col row) 1)
    ((= col 1) row)
    ((= col (dec row)) row)
    ((< 0 col row) (+ (pascal-triangle (dec row) col)
                      (pascal-triangle (dec row) (dec col))))
    (else 0)))


;; Clojure

;; (doseq [row (range 10)] (println "") 
;;        (doseq [col (range (inc row))] 
;;          (print " "(pascal-triangle row col))))

  ;; 1
  ;; 1  1
  ;; 1  2  1
  ;; 1  3  3  1
  ;; 1  4  6  4  1
  ;; 1  5  10  10  5  1
  ;; 1  6  15  20  15  6  1
  ;; 1  7  21  35  35  21  7  1
  ;; 1  8  28  56  70  56  28  8  1
  ;; 1  9  36  84  126  126  84  36  9  1
