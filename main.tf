provider "aws" {
  # Configuration options
  region = "us-east-2"
}

resource "aws_instance" "itcouldbeanything" {
  ami           = var.amioftheinstance
  instance_type = var.instancetype

  tags = {
    Name = "DemoInstance"
  }
}

output "thisistheinstancepublicip" {
  value = aws_instance.itcouldbeanything.public_ip

}

variable "amioftheinstance" {
  type    = string
  default = "ami-0cb91c7de36eed2cb"

}

variable "instancetype" {
  type    = string
  default = "t2.micro"

}