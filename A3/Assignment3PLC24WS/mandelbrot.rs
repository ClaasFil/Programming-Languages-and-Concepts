

use crate::complex::Complex;
use crate::image::Image;

// uncomment the code below and implement the functions 

pub fn check_pixel(c: Complex, max_iterations: usize) -> Option<usize> {
    let mut z = Complex::new(0., 0.);
    let mut iter: usize = 0;

    while iter < max_iterations {
        z = z*z + c;

        if z.mag() > 4.0 {
            return Some(iter);
        }
        iter += 1;
    }

    return None;

 }


pub fn generate_image(width: usize, height: usize, max_iterations: usize) -> Image {
//     // your code here
    let mut image = Image::new(width, height);

    for x in 0..width {
        for y in 0..height {
            let cx = (x as f64/width as f64 - 0.75) * 3.5;
            let cy = (y as f64/height as f64 - 0.5) * 2.0;

            let c = Complex {re: cx, im: cy};

            match check_pixel(c, max_iterations) {
                Some(_iterations) => {
                    if let Some(pixel) = image.get_mut(x, y) {
                        pixel.r = 255; 
                        pixel.g = 255;   
                        pixel.b = 255;   
                    }
                    else{
                        eprintln!("Error at image generation accesing non exsiting pixel");
                    }
                }
                None => {
                    if let Some(pixel) = image.get_mut(x, y) {
                        pixel.r = 0; 
                        pixel.g = 0;   
                        pixel.b = 0;   
                    }
                    else{
                        eprintln!("Error at image generation accesing non exsiting pixel");
                    }
                }
            }


        }
    }

    


    return image;
    
}
