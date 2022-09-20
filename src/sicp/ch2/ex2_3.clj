(ns sicp.ch1.ex2-3
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-35 :refer [abs]]))


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

(define make-rectangle cons)
(define start-point car)
(define end-point cdr)

(define (print-rectangle x)
  (newline)
  (display "Start")
  (print-point (start-point x))
  (display "End")
  (print-point (end-point x)))

(define (area s)
  (abs (*
        (- (x-point (start-point s))
           (x-point (end-point s)))
        (- (y-point (start-point s))
           (y-point (end-point s))))))

(define (perimeter s)
  (+ (abs (* 2
             (- (x-point (start-point s))
                (x-point (end-point s)))))
     (abs (* 2
             (- (y-point (start-point s))
                (y-point (end-point s)))))))

(println (area (make-rectangle 
                (make-point 0 0)
                (make-point 10 10))))

(println (perimeter (make-rectangle 
                    (make-point 10 10)
                    (make-point 0 0))))

(define (make-rectangle-wh p w h)
  (make-rectangle 
   p
   (make-point (+ w (x-point p))
               (+ h (y-point p)))))

(println (area (make-rectangle-wh 
                (make-point 0 0) 10 10)))

(println (perimeter (make-rectangle-wh
                     (make-point 10 10) -10 -10)))



