import matplotlib.pyplot as plt
import numpy as np

plt.style.use('dark_background')

data = np.loadtxt('variance.data')

x = data[:, 0]   # Matrix dimension
y1 = data[:, 1]  # General Matrix variance
y2 = data[:, 2]  # Triangular Matrix variance

fig, (ax1, ax2) = plt.subplots(2, 1, figsize=(10, 8))

# General matrix plot
ax1.plot(x, y1, color='red', linewidth=2)
ax1.set_title('Variance of Determinant (General Matrix)', fontsize=13, pad=10)
ax1.set_ylabel(r'$\mathrm{Var}(\mathrm{det}(M))$', fontsize=12)
ax1.set_xlabel('Matrix size $n$', fontsize=11)
ax1.set_yscale('log')
ax1.set_xlim(0, 50)
ax1.set_ylim(10**-5, 10**15)
ax1.grid(True, which="both", linestyle="--", color='gray', alpha=0.3)

# Triangular matrix plot
ax2.plot(x, y2, color='blue', linewidth=2)
ax2.set_title('Variance of Determinant (Triangular Matrix)', fontsize=13, pad=10)
ax2.set_ylabel(r'$\mathrm{Var}(\mathrm{det}(M))$', fontsize=12)
ax2.set_xlabel('Matrix size $n$', fontsize=11)
ax2.set_yscale('log')
ax2.set_xlim(0, 50)
ax2.set_ylim(10**-20, 10**0)
ax2.grid(True, which="both", linestyle="--", color='gray', alpha=0.3)

plt.tight_layout()

plt.show()
