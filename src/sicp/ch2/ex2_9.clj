^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns sicp.ch1.ex2-9
  ""
   (:refer-clojure :exclude [define cons let cond])
   (:require [sicp.scheme.core :refer :all]
             [sicp.scheme.cons :refer [cons car cdr]]
             [nextjournal.clerk :as clerk]
             [sicp.ch1.ex1-35 :refer [abs]]
             [sicp.ch1.examples :refer [square]]))

;; ## Show that width of 2 intervals is a function of the widths of the intervals
;; Tex editor https://atomurl.net/math/
^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex "width = \\frac{(l + u)} {2}
\\\\
width_{ij} = width_{i} + width_{j}
\\\\
 = \\frac{(l_i + u_i)} {2} + \\frac{(l_j + u_j)} {2} \\\\
 = \\frac{(l_i + l_j)} {2} + \\frac{(u_i + u_j)} {2} \\\\
\\\\
\\\\
\\text{sample matrix}
\\\\
\\begin{pmatrix} lower_{1} & upper_{1}\\end{pmatrix} 
+
\\begin{pmatrix} lower_{2} & upper_{2}\\end{pmatrix} 
=
\\begin{pmatrix} (lower_{1} + lower_{2})& (upper_{1} + upper_{2})\\end{pmatrix} 


")

