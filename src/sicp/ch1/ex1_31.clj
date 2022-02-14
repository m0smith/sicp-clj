(ns sicp.ch1.ex1-31
  ""
   (:refer-clojure :exclude [cond define next])
   (:require [sicp.scheme.core :refer :all]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.examples :refer [square]]))

;; https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-12.html

;; ##  Product recursive
(define (product-recursive term a next b)
  (if (> a b)
      1
      (* (term a)
         (product-recursive term (next a) next b))))

(define (product-iterative term a next b)
  (define (iter a result)
    (if (> a b)
        result
        (iter (next a) (* result (term a)))))
  (iter a 1))

;; ## Factorial
(define  (! n)
  (product-iterative identity 2 inc n))

;; ## Pi approximation
;; Formula to generate the numerator, stating at 1
(map #(+ 2 (* 2 (quot % 2))) (range 1 10))



;; Formula to generate the numerator, stating at 1
(map #(+ 1 (* 2 (quot (inc %) 2))) (range 1 10))

;; terms is the number of terms to use in the approximation

(define (pi terms)
  (define (numerator n)
    (+ 2 (* 2 (quot n 2))))
  (define (denominator n)
    (+ 1 (* 2 (quot (inc n) 2))))
  (define (f n)
    (/ (product-iterative numerator 1 inc terms)
       (product-iterative denominator 1 inc terms)))
  (* 4 (f terms)))

;; ## Clojure

(defn product-iterative [term a next b]
  (letfn [(iter [a result]
            (if (> a b) 
              result 
              (recur (next a) (* (term a) result ))))]
    (iter a 1)))

;; There is some special handling of BigDecimal division in Java
;;  See with-precision and bigdec functions
;;  By default / with integers and big integers creates a Ratio, which doesn't like
;;  Big numbers. Using bigdec to force floating division

(defn pi [terms]
  (letfn
    [(numer [n] (+ 2 (* 2 (quot n 2))))
     (denom [n] (+ 1 (* 2 (quot (inc n) 2))))
     (f [n] (with-precision 10
              (/
               (bigdec (product-iterative numer 1N inc terms))
               (with-precision 10
                 (bigdec (product-iterative denom 1N inc terms))))))]
    (* 4 (f terms))))

(product-recursive identity 1 inc 10)

(product-iterative identity 1 inc 10)

(! 10)

(pi 1)
(pi 100)
(pi 500)
(pi 10000)
(comment
        (pi 100000))






