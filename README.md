# 🚀 Matvar

This project studies the statistical properties of matrix determinants, in particular it analyses
 the variance of determinants in random general and tri-diagonal matrices, 
looking at its relationship with dimension.

## 📸 Visualisation

The program graph.py uses the variance data from the project to produce the following plots

![Variance Plots](variance_plots.png)

As you can see, as the dimension of a general matrix increases 
so does the variance of the determinant. On the other hand, you 
can see it is the opposite case for the triangular matrix, where
there is a linear inverse relationship between dimension and variance.

## 📦 Installation

Clone the repository and install the dependencies in requirement.txt:

```bash
git clone https://github.com/hhyde32/matvar.git
cd matvar
pip install -r requirements.txt
```

## 🛠 Usage

Run the following command to compile and save the project data to variance.data. Note this may take around a minute depending on your system.

```bash
javac Main.java
java Main > variance.data
```

To generate the graphs above, run this command:

```bash
python graph.py
```

## 🧰 Tech Stack

- Java
- Python
