

// implement the Complex struct and traits below

pub struct Complex {
    pub re: f64,
    pub im: f64, 
}

impl Complex {
    pub fn new(re: f64, im: f64) -> Self {
        Self {re, im}
    }

    pub fn mag(self) -> f64 {
        self.re.powi(2) + self.im.powi(2)
    }

    
}

impl Clone for Complex {
    fn clone(&self) -> Self {
        Self {
            re: self.re,
            im: self.im
        }
    }
}

impl Copy for Complex {}

impl std::ops::Add for Complex {
    type Output = Self; // Specify the result type (another Complex)

    fn add(self, other: Self) -> Self {
        Self {
            re: self.re + other.re,
            im: self.im + other.im,
        }
    }
}

impl std::ops::Mul for Complex{
    type Output = Self;

    fn mul(self, other: Self) -> Self {
        Self {
            re: self.re * other.re - self.im * other.im, 
            im: self.re * other.im + other.re * self.im,
        }
    }
}








