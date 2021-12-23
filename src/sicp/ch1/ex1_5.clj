(ns sicp.ch1.ex1-5
  "NOTE: Running this code will produce an error"
   (:refer-clojure :exclude [cond define])
   (:require [sicp.scheme.core :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Scheme
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (p) (p)) 
;; sicp.ch1.ex1-5> (p)
;; Execution error (StackOverflowError) at sicp.ch1.ex1-5/p (ex1_5.clj:6).

(define (test x y)
  (if (= x 0)
      0
      y))

;;(test 0 (p))

;; Applicative-order:
;;     Will result in a stack overflow as (p) is evaluated before calling `test`

;; Normal-over:
;;     Calls test passing the unevaluated `(p)` to evaluate it later.  This will succeed.
;;     We can simulate this in Clojure with a macro.

(defmacro test-normal-order [x y]
  `(if (= ~x 0) 
     0
     ~y))    

(test-normal-order 0 (p))

;; sicp.ch1.ex1-5> (test-normal-order 0 (p))
;; 0

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Clojure
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn p [] (p))

(defn test [x y] (if (= x 0) 0 y))

