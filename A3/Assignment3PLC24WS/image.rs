use std::fmt;


// implement the Pixel struct and traits below
/// A struct representing a single pixel with RGB color channels.
pub struct Pixel {
    pub r: u8, // Red color channel (0-255)
    pub g: u8, // Green color channel (0-255)
    pub b: u8, // Blue color channel (0-255)
}

impl Pixel {
    /// Creates a new `Pixel` with the given RGB values.
    #[allow(dead_code)]
    pub fn new(r: u8, g: u8, b: u8) -> Self {
        Self { r, g, b }
    }
}

impl Clone for Pixel {
    fn clone(&self) -> Self {
        Self {
            r: self.r,
            g: self.g,
            b: self.b,
        }
    }
}

impl Copy for Pixel {}

impl fmt::Display for Pixel {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{} {} {}", self.r, self.g, self.b)
    }
}

impl std::fmt::Debug for Pixel {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "Pixel {{ r: {}, g: {}, b: {} }}", self.r, self.g, self.b)
    }
}

impl PartialEq for Pixel {
    fn eq(&self, other: &Self) -> bool {
        self.r == other.r && self.g == other.g && self.b == other.b
    }
}









// implement the Image struct and traits below

pub struct Image {
    pub width:  usize, 
    pub height: usize,
    pub data: Vec<Pixel>
}

impl Image {
    /// Creates a new `Pixel` with the given RGB values.
    pub fn new(width: usize, height: usize) -> Image {
        let length = width * height;

        let default_pixel = Pixel { r: 0, g: 0, b: 0 };
        let data: Vec<Pixel> = vec![default_pixel; length];
        Self { width, height, data }
    }
    #[allow(dead_code)]
    pub fn get(&self, x: usize, y: usize) -> Option<&Pixel> {
        if x < self.width && y < self.height {
            let index = y * self.width + x;
            Some(&self.data[index])
        } else {
            None
        }
    }

    pub fn get_mut(&mut self, x: usize, y: usize) -> Option<&mut Pixel> {
        if x < self.width && y < self.height {
            let index = y * self.width + x;
            Some(&mut self.data[index])
        } else {
            None
        }
    }
    #[allow(dead_code)]
    pub fn get_mandelbrot_pixels(&self) -> usize {
        self.height * self.width
    }



}







