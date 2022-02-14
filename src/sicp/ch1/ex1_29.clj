(ns sicp.ch1.ex1-29
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

#_(comment 
  (clerk/serve! {:watch-paths ["src"]})
  )
;; ## Scheme

(define (cube x) (* x x x))

(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term (next a) next b))))

(define (simpson-rule f a b n)
  (define h (/(- b a) n))
  (define (term k) 
    (cond 
      ((zero? k) (f k))
      ((even? k) (* 2 (f (+ a (* k h)))))
      (else (* 4 (f (+ a (* k h)))))))
  (/ (* h (sum term 0 inc n))
     3))

;; ## Clojure

(defn simpson-rule [f a b n]
  (let [h (/ (- b a) n)]
    (letfn [(term [k] (clojure.core/cond
                        (zero? k) (f k)
                        (even? k) (* 2 (f (+ a (* k h))))
                        :else (* 4 (f (+ a (* k h))))))]
      (/ (* h (sum term 0 inc n)) 3))))

(simpson-rule cube 0 1 1000)

