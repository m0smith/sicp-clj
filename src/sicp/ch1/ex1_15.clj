(ns sicp.ch1.ex1-15
  ""
   (:refer-clojure :exclude [cond define let])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [good-enough? improve]]))

;; Scheme
(define (abs x) 
  (cond 
    ((< x 0) (- x))
    (else x)))
(define (cube x) (* x x x))
(define (p x) (- (* 3 x) (* 4 (cube x))))
(define (sine angle)
   (if (not (> (abs angle) 0.1))
       angle
       (p (sine (/ angle 3.0)))))

#_(define (sine angle)
  (define call-count (atom 0))
  (define (sine* angle)
    (if (not (> (abs angle) 0.1))
      (do 
        (println "Call count:" @call-count)
        angle)
      (do
        (swap! call-count inc)
        (p (sine* (/ angle 3.0))))))
  (sine* angle))


;; Clojure 

(defn sine [angle]
  (clojure.core/let [call-count (atom 0)]
    (letfn [(sine*
              [angle]
              (if (not (> (abs angle) 0.1))
                (do (println "Call count:" (deref call-count)) angle)
                (do (swap! call-count inc) (p (sine* (/ angle 3.0))))))]
      (sine* angle))))


