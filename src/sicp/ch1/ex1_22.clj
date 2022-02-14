  (ns sicp.ch1.ex1-22
  ""
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]
             [sicp.ch1.examples :refer [square]]))

(define (divides? a b)
  (= (remainder b a) 0))

(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))

(define (smallest-divisor n)
  (find-divisor n 2))

(define (prime? n)
  (= n (smallest-divisor n)))

(define (report-prime elapsed-time)
  (display " *** ")
  (display elapsed-time)
  true)

(define (start-prime-test n start-time)
  (if (prime? n)
      (report-prime (- (runtime) start-time))))

(define (timed-prime-test n)
  (newline)
  (display n)
  (start-prime-test n (runtime)))


(define (search-for-primes starting-n)
  (define (search-for-primes* n number-needed)
    (cond ((< number-needed 1) true)
          ((even? n) (search-for-primes* (+ 1 n) number-needed))
          ((timed-prime-test n) (search-for-primes* (+ n  2) (-  number-needed 1)))
          (else (search-for-primes* (+ n 2) number-needed))))
  (search-for-primes* starting-n 3))

;; Clojure

(defn search-for-primes [starting-n]
  (letfn
    [(search-for-primes*
       [n number-needed]
       (clojure.core/cond
         (< number-needed 1) true
         (even? n) (recur (+ 1 n) number-needed)
         (timed-prime-test n) (recur (+ n 2) (- number-needed 1))
         :else (recur (+ n 2) number-needed)))]
    (search-for-primes* starting-n 3)))

