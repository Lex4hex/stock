import React, {Component} from 'react';
import stockService from '../service/stock.service'

// Stock creation component
export default class Create extends Component {

    constructor(props) {
        super(props);
        this.onChangeStockName = this.onChangeStockName.bind(this);
        this.onChangeStockPrice = this.onChangeStockPrice.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            stockName: '',
            stockPrice: ''
        }
    }

    onChangeStockName(e) {
        this.setState({
            stockName: e.target.value
        });
    }

    onChangeStockPrice(e) {
        this.setState({
            stockPrice: e.target.value
        })
    }

    // Sends a create stock API request of form submit
    onSubmit(e) {
        e.preventDefault();
        const request = {
            stockName: this.state.stockName,
            stockPrice: this.state.stockPrice
        };

        stockService.createStock(request)
            .then(res => console.log(res.data))
            .catch(error => {
                console.log(error.response);
                this.setState({hasErrors: true, message: error.response.data.errorMessage})
            });

        this.setState({
            stockName: '',
            stockPrice: ''
        })
    }

    render() {
        let error;

        // Render error
        if (this.state.hasErrors) {
            error = <div className="alert alert-danger">
                <strong>Error!</strong> {this.state.message}.
            </div>;
        }

        return (
            <div style={{marginTop: 10}}>
                <h3>Add a new stock</h3>
                {error}
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Stock name: </label>
                        <input
                            type="text"
                            className="form-control"
                            value={this.state.stockName}
                            onChange={this.onChangeStockName}
                        />
                    </div>
                    <div className="form-group">
                        <label>Stock price: </label>
                        <input type="text"
                               className="form-control"
                               value={this.state.stockPrice}
                               onChange={this.onChangeStockPrice}
                        />
                    </div>
                    <div className="form-group">
                        <input type="submit" value="Create Stock" className="btn btn-primary"/>
                    </div>
                </form>
            </div>
        )
    }
}
