(ns sicp.ch1.ex1-14
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [good-enough? improve]]))

;; Scheme

(define (first-denomination kinds-of-coins)
  (cond ((= kinds-of-coins 1) 1)
        ((= kinds-of-coins 2) 5)
        ((= kinds-of-coins 3) 10)
        ((= kinds-of-coins 4) 25)
        ((= kinds-of-coins 5) 50)))

(define (cc amount kinds-of-coins)
  (cond ((= amount 0) 1)
        ((or (< amount 0) (= kinds-of-coins 0)) 0)
        (else (+ (cc amount
                     (- kinds-of-coins 1))
                 (cc (- amount
                        (first-denomination kinds-of-coins))
                     kinds-of-coins)))))

(define (count-change amount)
  (cc amount 5))


;; Clojure


(defn cc [amount kinds-of-coins]
  (let [call-count (atom 0)
        max-depth (atom 0)]
    (letfn [(cc* [amount kinds-of-coins depth]
              (swap! call-count inc)
              (swap! max-depth #(max depth %))
              (println (format (str "%" (inc depth) "s %3d") "" depth) "cc" amount kinds-of-coins)
              (clojure.core/cond
                (= amount 0) 1
                (or (< amount 0) (= kinds-of-coins 0)) 0
                :else (+
                       (cc* amount (- kinds-of-coins 1) (inc depth))
                       (cc*
                        (- amount (first-denomination kinds-of-coins))
                        kinds-of-coins
                        (inc depth)))))]
      (let [rtnval (cc* amount kinds-of-coins 0)]
        (println "Call Count:" @call-count)
        (println "Max Depth:" @max-depth)
        rtnval))))


