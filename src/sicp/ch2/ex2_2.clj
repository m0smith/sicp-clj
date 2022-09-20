(ns sicp.ch1.ex2-2
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-45 :refer [average]]))


(define make-point cons)
(define x-point car)
(define y-point cdr)

(define (print-point x)
  (newline)
  (display "(")
  (display (x-point x))
  (display ",")
  (display (y-point x))
  (display ")"))

(define make-segment cons)
(define start-segment car)
(define end-segment cdr)

(define (print-segment x)
  (newline)
  (display "Start")
  (print-point (start-segment x))
  (display "End")
  (print-point (end-segment x)))

(define (midpoint-segment s)
  (make-point
   (float (average (x-point (start-segment s))
                   (x-point (end-segment s))))
   (float (average (y-point (start-segment s))
                   (y-point (end-segment s))))))

(print-point (midpoint-segment
              (make-segment
               (make-point 1 11)
               (make-point 2 2))))




